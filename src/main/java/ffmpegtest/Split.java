package ffmpegtest;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;  

public class Split {
    private ObjectMapper mapper = new ObjectMapper();  

	
	public  Map getMovieInfo(String filePath) {
        String cmd =  "ffprobe -v quiet -print_format json -show_format -i " + filePath;
        System.out.println(cmd);
        try {
            Runtime rt = Runtime.getRuntime();
            Process process = rt.exec(cmd);
            BufferedInputStream in = new BufferedInputStream(process.getInputStream());
            BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
            StringBuffer sb = new StringBuffer();
            String lineStr;
            while ((lineStr = inBr.readLine()) != null)
            {
                sb.append(lineStr);
                //System.out.println(lineStr);
            }
            process.waitFor();
            inBr.close();
            in.close();
            //System.out.print(sb);
            return analyseInfo(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	private Map analyseInfo(String json) throws IOException, JsonMappingException, IOException {
		//System.out.println(json);
        HashMap map = mapper.readValue(json, HashMap.class);
        Map format = (Map) map.get("format");
        //String bitrate = (String) format.get("bit_rate");
        //System.out.println(map.toString());
        return  format;
	}

	public int split(String filePath,String outputPath) throws IOException, InterruptedException{
		Map movieInfo = getMovieInfo(filePath);
		double size = Double.valueOf( (String) movieInfo.get("size"));
		double start_time =Double.valueOf(  (String)movieInfo.get("start_time"));
		double duration = Double.valueOf( (String)movieInfo.get("duration"));
		int blockNum=(int) (size/(6*1024*1024))+1;
		double length = duration/blockNum;
		
        Runtime rt  =Runtime.getRuntime();
        String cmd;
        Process process;
        
        Map splitInfo;
        double splitStartTime = start_time;
        int num;
        for(num=0;splitStartTime+length<start_time+duration;num++){
            cmd = "ffmpeg -i "+filePath+" -ss "+splitStartTime+"  -t "+length+" -vcodec copy -acodec copy "+outputPath+num+".mkv";
            System.out.println(cmd);
            process = rt.exec(cmd);
            process.waitFor();
            process.destroy();
            splitInfo=getMovieInfo(outputPath+num+".mkv");
            splitStartTime += Double.valueOf((String)splitInfo.get("duration"));
            if((splitStartTime+length)>(start_time+duration))
            	length = start_time+duration-splitStartTime;
        }
		return num;
	}
}
