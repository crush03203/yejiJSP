package kr.or.ddit.ui;

import java.util.Map;

public class DefaultPaginationManager implements PaginationManager {

	private Map<String, PaginationRenderer> rendererMap;
 	
	//기본생성자 없애고 맵을 반드시 넣어야 객체 생성되는 구조로 변경됨 (밑에서 세번째로 씀)
	public DefaultPaginationManager(Map<String, PaginationRenderer> rendererMap) {
		super();
		this.rendererMap = rendererMap;
	}


	@Override
	public PaginationRenderer rendererType(String type) {
		PaginationRenderer render = rendererMap.get(type);
		if(render==null)
			render = new DefaultPaginationRenderer();
		return render;
	}

}
