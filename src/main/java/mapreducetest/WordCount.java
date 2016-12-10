package mapreducetest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.StringTokenizer;
 
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.input.LineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
 
public class WordCount {
 
  public static class TokenizerMapper 
       extends Mapper<Object, Text, Text, IntWritable>{
 
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();
 
    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
      //StringTokenizer itr = new StringTokenizer(value.toString());
      String local = "/home/hadoop/test/";
      //while (itr.hasMoreTokens()) {
      //  word.set(itr.nextToken());
        String lalala = ((FileSplit)context.getInputSplit()).getPath().toString();
        String fileName = ((FileSplit)context.getInputSplit()).getPath().getName();
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(lalala),conf);
        FSDataInputStream fsdi = fs.open(new Path(lalala));
        OutputStream output = new FileOutputStream(local+fileName);
        IOUtils.copyBytes(fsdi, output, 4096,true);
        
        word.set(lalala);
        context.write(word, one);
    //  }
    }
  }
 
  public static class IntSumReducer 
       extends Reducer<Text,IntWritable,Text,IntWritable> {
    private IntWritable result = new IntWritable();
 
    public void reduce(Text key, Iterable<IntWritable> values, 
                       Context context
                       ) throws IOException, InterruptedException {
      int sum = 0;
      for (IntWritable val : values) {
        sum += val.get();
      }
      result.set(sum);
      context.write(key, result);
    }
  }
 
  public void run() throws Exception {
    Configuration conf = new Configuration();
    //String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
    //if (otherArgs.length != 2) {
     // System.err.println("Usage: wordcount <in> <out>");
     // System.exit(2);
   // }
    Job job = new Job(conf, "word count");
    job.setJarByClass(WordCount.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setCombinerClass(IntSumReducer.class);
    job.setReducerClass(IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    job.setInputFormatClass(NonSplittableTextInputFormat.class);
    FileInputFormat.addInputPath(job, new Path("input"));
    FileOutputFormat.setOutputPath(job, new Path("output"));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }


public static class NonSplittableTextInputFormat extends TextInputFormat{
	@Override
	protected boolean isSplitable(JobContext context,Path file){
		return false;
	}
}
}