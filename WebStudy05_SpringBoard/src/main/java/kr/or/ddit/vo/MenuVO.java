package kr.or.ddit.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode(of="menuCode")
@RequiredArgsConstructor
//@NoArgsConstructor
public class MenuVO {
	private String menuCode;
	private final String menuText;
	private final String menuURL;
	private String menuColor;
}
