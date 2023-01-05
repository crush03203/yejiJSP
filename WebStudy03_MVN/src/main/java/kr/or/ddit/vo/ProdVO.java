package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Part;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.mvc.multipart.MultipartFile;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 상품 하나의 정보(분류명, 거래처 정보 포함)를 담기위한 객체
 * PROD(1) : BUYER(1) -> has a 관계 
 *
 */

@Data
@EqualsAndHashCode(of="prodId")
@ToString(exclude="prodDetail") //부하가 걸릴거같은 거는 tostring에서 제외시켜줘야됨
public class ProdVO implements Serializable  {
	
	private int rnum; //일련번호
	
	
	//sql문이 not null로 설정되어있으면 @NotBlank  사용 
	@NotBlank(groups=UpdateGroup.class) //InsertGroup.class일때는 prodId 검증 안함/UpdateGroup.class일때만 검증함
	private String prodId;
	@NotBlank
	private String prodName;
	
	@NotBlank
	private String prodLgu;
	private String lprodNm;  //상품명
	
	@NotBlank
	private String prodBuyer;
	private BuyerVO buyer; //has a 관계 - 거래처명
	
	@NotNull //integer 타입의 not null은 @NotBlank가 아니라 @NotNull @Min(0)로 표현해야됨 
	@Min(0)
	private Integer prodCost;
	@NotNull //integer 타입의 not null은 @NotBlank가 아니라 @NotNull @Min(0)로 표현해야됨 
	@Min(0)
	private Integer prodPrice;
	@NotNull //integer 타입의 not null은 @NotBlank가 아니라 @NotNull @Min(0)로 표현해야됨 
	@Min(0)
	private Integer prodSale;
	
	@NotBlank
	private String prodOutline;
	private String prodDetail;
	
	@NotBlank
	private String prodImg; //prod 테이블 조회용 프로퍼티
	
	private MultipartFile prodImage; 
	
	@NotNull //integer 타입의 not null은 @NotBlank가 아니라 @NotNull @Min(0)로 표현해야됨 
	@Min(0)
	private Integer prodTotalstock;
	private String prodInsdate;
	
	@NotNull //integer 타입의 not null은 @NotBlank가 아니라 @NotNull @Min(0)로 표현해야됨 
	@Min(0)
	private Integer prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private Integer prodQtyin;
	private Integer prodQtysale;
	private Integer prodMileage;

	// private MemberVO memeber; //하나의 상품은 한명밖에 구매 못함
	//private List<MemberVO> memeber;  //하나의 상품은 여러명 구매할수있는데 한명이 여러개 구매할수있음 
	private Set<MemberVO> memberSet; //구매자의 정보는 중복될수없다   /  has many 관계 
	
	//상품구매자수
	private int memCount;
	
	
}
