package kr.or.ddit.validate;

import javax.validation.groups.Default;

/**
 * Marker interface 
 * 기능을수행하기 위한 인터페이스 아님 
 * 검증 조건을 분리하기 위한 목적
 * @author PC-20
 *
 */

//기본그룹이면서 insert그룹으로 만들기  => extends Default
public interface InsertGroup extends Default {

}
