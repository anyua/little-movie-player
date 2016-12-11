package hdfstest;


import com.hdfs.dao.FileSystemStatus;

import ffmpegtest.Split;
import mapreducetest.WordCount;

import java.io.IOException;

public class Test {
	public String login() throws Exception{
		FileSystemStatus status = new FileSystemStatus();
		status.getMovieList();
		//WordCount task = new WordCount();
		//task.run();
		trysplit();
		return "success";
	}
	public  void  trysplit() throws IOException, InterruptedException{
		Split split=new Split();
		String filePath = "/home/hadoop/split.mp4";
		String outputPath = "/home/hadoop/test/split/";
		split.split(filePath, outputPath);
	}
}
