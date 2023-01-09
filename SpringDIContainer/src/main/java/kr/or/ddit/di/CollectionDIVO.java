package kr.or.ddit.di;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionDIVO {
	private List<?> sampleList;
	private Set<String> sampleSet;
	private Map<String, ?> sampleMap;
	
	private Properties props;
	
	private String[] array;
}
