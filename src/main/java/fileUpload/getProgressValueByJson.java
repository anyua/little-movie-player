package fileUpload;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobStatus;
import org.apache.hadoop.mapred.RunningJob;

public class getProgressValueByJson {
	private int progressValue = 0;
	private String jobName;
	private String jobId;
	public String getValue() throws IOException{
		progressValue+=3;
		//System.out.println(jobName);
		//float steping = 0;
		//Configuration conf = new Configuration();
	    //JobClient client = new JobClient(conf);
	   // RunningJob runningjob = client.getJob(jobId);
	    //if(runningjob!=null)
	    	//steping = runningjob.getJobStatus().getMapProgress();
	    //System.out.println(steping);
	    // JobStatus[] runningjobs=client.getAllJobs();
	   // for(JobStatus i:runningjobs){
	    //	if(i.getJobName().equals(jobName)&&i.getRunState()==1)
	   // 	{
	    //		steping = i.mapProgress();
	    //		String tmp = i.getJobId();
	    //		System.out.println(steping);
	    //		System.out.println(tmp);
	   	//	break;
	   // 	}
	   // }
	    //progressValue = (int) (steping*100);
		//System.out.println(progressValue);
		return "success";
	}
	public int getProgressValue() {
		return progressValue;
	}
	public void setProgressValue(int progressValue) {
		this.progressValue = progressValue;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

}
