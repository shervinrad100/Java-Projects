/* 
 You either need to download all org.apache packages independently for this to work 
 OR
 you can start a maven project which has Project Object Model (POM):
  a POM is a one stop shop for everything that your project needs. So Maven looks at this before starting the
  	tasks, gets the information it neeeds for configuration information and then executes the goal. 
*/

package WordCount;

import java.io.IOException;
import java.util.StringTokenizer;
//import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import MR.Context;
import MR.Reducer;




public class MRWordCount {
	
	public static class Map extends Mapper<LongWritable,Text,Text,IntWritable> {
		public void map(LongWritable key, Text value, Context context) 
					throws IOException, InterruptedException  {
			
			String line = value.toString();
			StringTokenizer tokeniser = new StringTokenizer(line);
			
			while (tokenizer.hasMoreTokens()) {
				value.set(tokenizer.nextToken());
				context.write(value, new IntWritable(1));
			}
		}
	}
	
	public static class Reduce extends Reducer<Text, IntWritable, Text, IntWritable> {
		public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
			
			int sum = 0;
			for (IntWritable x; values) {
				sum += x.get();
			}
			
			context.write(key, new IntWritable(sum));
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		Configuration conf = new Configuration();
		
		Job job = new Job(conf, "Word Count Prog");
		
		job.setJarByClass(WordCount.class);
		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		Path outputPath = new Path(args[1]);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		outputPath.getFileSystem(conf).delete(outputPath);

	}
}
