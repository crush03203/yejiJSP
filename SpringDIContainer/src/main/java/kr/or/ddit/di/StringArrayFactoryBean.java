package kr.or.ddit.di;

import org.springframework.beans.factory.FactoryBean;

public class StringArrayFactoryBean implements FactoryBean<String[]>{

	@Override
	public String[] getObject() throws Exception {
		return new String[] {"element1","element2"};
	}

	@Override
	public Class<?> getObjectType() {
		return String[].class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
