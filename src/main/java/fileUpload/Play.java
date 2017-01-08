package fileUpload;

import java.io.IOException;

import operations.MapReduceOperation;

public class Play {
	private String name ;
	private String jobName;
	private String jobId;
	private MapReduceOperation mo = new MapReduceOperation();
	public String playMovie() throws ClassNotFoundException, IOException, InterruptedException{
		String inputPath = "video/"+name;
		//jobName = mo.transCode(inputPath, "output");
		jobId = mo.transCode(inputPath, "output");
		return "success";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
