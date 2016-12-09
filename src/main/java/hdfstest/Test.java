package hdfstest;


import com.hdfs.dao.FileSystemStatus;

import java.io.IOException;


public class Test {
	public String login() throws IOException{
		FileSystemStatus status = new FileSystemStatus();
		status.getMovieList();
		return "success";
	}
}
