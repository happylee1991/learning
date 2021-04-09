package wordcount;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.junit.Test;

public class App {
    @Test
    public void testWordCountBatch() throws Exception {
        // 创建执行环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        // 从文件中读取数据
        String inputPath = "/Users/duang/IdeaProjects/learning/flink/src/main/resources/word.txt";
        DataSet<String> inputDataSet = env.readTextFile(inputPath);
        // 空格分词打散之后，对单词进行 groupby 分组，然后用 sum 进行聚合
        DataSet<Tuple2<String, Integer>> wordCountDataSet =
                inputDataSet.flatMap(new MyFlatMapper())
                        .groupBy(0)
                        .sum(1);
        // 打印输出
        wordCountDataSet.print();
    }

    @Test
    public void testWordCountStream() throws Exception {
        StreamExecutionEnvironment env =
                StreamExecutionEnvironment.getExecutionEnvironment();

//        ParameterTool parameterTool = ParameterTool.fromArgs(args);
//        String host = parameterTool.get("host");
//        int port = parameterTool.getInt("port");
        String host = "localhost";
        int port = 7777;

        DataStream<String> inputDataStream = env.socketTextStream(host, port);
        DataStream<Tuple2<String, Integer>> wordCountDataStream = inputDataStream
                .flatMap(new MyFlatMapper())
                .keyBy(0)
                .sum(1);

        wordCountDataStream.print().setParallelism(1);
        env.execute();

    }


}
