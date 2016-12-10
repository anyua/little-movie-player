package hdfstest;


import com.hdfs.dao.FileSystemStatus;

import mapreducetest.WordCount;

import java.io.IOException;


public class Test {
	public String login() throws Exception{
		FileSystemStatus status = new FileSystemStatus();
		status.getMovieList();
		WordCount task = new WordCount();
		task.run();
		return "success";
	}
}
