package com.firstestspringbatch.springbatchexample1.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.firstestspringbatch.springbatchexample1.model.UserEntity;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	
	@Bean
	public Job job(
			JobBuilderFactory jobBuilderFactory, 
			StepBuilderFactory stepBuilderFactory,
            ItemReader<UserEntity> itemReader,
            ItemProcessor<UserEntity, UserEntity> itemProcessor,
            ItemWriter<UserEntity> itemWriter
    ) {
		Step step = stepBuilderFactory.get("ETL-file-load")
				.<UserEntity, UserEntity>chunk(100)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.build();
		
		return jobBuilderFactory.get("ETL-Load")
				.incrementer(new RunIdIncrementer())
				.start(step)
				.build();
	}
	
	@Bean
	public FlatFileItemReader<UserEntity> fileItemReader(@Value("${input}") Resource resource) {
		FlatFileItemReader<UserEntity> flatFileItemReader = new FlatFileItemReader<>();
		flatFileItemReader.setResource(resource);
		flatFileItemReader.setName("CSV-Reader");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());
		return flatFileItemReader;
	}
	
	@Bean
	public LineMapper<UserEntity> lineMapper() {
		DefaultLineMapper<UserEntity> defaultLineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames("id", "name", "dept", "salary");
		
		BeanWrapperFieldSetMapper<UserEntity> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(UserEntity.class);
		
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		
		return defaultLineMapper;
	}
}
