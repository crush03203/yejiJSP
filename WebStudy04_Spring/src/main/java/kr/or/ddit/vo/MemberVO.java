package kr.or.ddit.vo;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.InsertGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * VO(Value Object), DTO(Data Transfer Object), Java Bean, Model
 *	 
 *
 *[ property ] : (자바에서 제공하는 데이터 딕셔너리를 참고해서 스키마 데이터사용할수있음) 
 * VARCHAR2 => String
 * CHAR => String
 * DATE => String  .. 
 *
 * JavaBean 규약의 조건 
 * 1. 값을 담을 수 있는 property를 정의해야됨   => String memId,  private String memBir ... 
 * 2. property 캡슐화해야됨 => private 붙이기 
 * 3. 캡슐화된 property에 접근할 수 있는 인터페이스 제공 => getter/setter 만들기 
 * 		- get[set] + property명의 첫문자를 대문자로 ->  결과물 : camel case 
 * 4. 객체의 상태 비교 방법 제공 => equals(), hashCode() => primary key 값만 만들면됨 
 *   ==, equals 
 * 5. 객체의 상태 확인 방법 제공 => toString
 *   상태를 노출 시키면 안되는 것들은 제외해야됨 (ex. password )  
 * 6. 객체 직렬화 가능 해야됨 => implements Serializable
 *   숨겨야 하는 것들은 transient 붙여서 직렬화 피해줄 것 : private transient String memPass;
 *  
 * 
 *  
 *  회원관리를 위한 Domain Layer
 *  : 한사람의 회원정보(구매기록 포함)를 담기 위한 객체 
 *    MEMBER(1) : PROD(N) -> HAS MANY 관계
 *    1 : 1  -> HAS A 관계 
 *    
 *    
 *   
 *  ** 데이터매퍼나 ORM을 이용한 테이블 조인 방법
 *  	ex) 회원 정보 상세 조회시 구매 상품 기록을 함께 조회함 
 *  1. 대상 테이블 선택 
 *  	MEMBER(기본정보), CART(CART_MEMBER, CART_PROD ) ,PROD(상품에 대한 정보)  
 *  2. 각테이블로부터 데이터를 바인딩할 VO설계
 *  	MemberVo, ProdVO
 *  3. 각테이블의 relation을 VO사이에 has 관계로 반영
 *  	1(메인테이블):N -> has many관계 형성, MemberVO has many ProdVO(many라는 구조를 표현하기 위해 collection태그 사용)
 *  	1(메인테이블):1 -> has a 관계 형성    , ProdVO has a BuyerVO
 *  4. resultType 대신 resultMap으로 바인딩 설정 
 *  	has many관계 : collection 사용
 *  	has a   관계 : assocation 사용
 *    
 *  
 */


/*@Getter  //lombok으로 getter추가 
@Setter //lombok으로 setter추가 
@ToString(exclude= {"memPass", "memRegno1", "memRegno2"}) //lombok으로 @ToString추가  =>memPass, memRegno1, memRegno2를 제외한 프로퍼트 전부 toString
@EqualsAndHashCode(of="memId") //lombok으로 Equals ,HashCode추가  =>  memId얘만 같으면 같은 취급 
@NoArgsConstructor //lombok으로 기본생성자 추가 
*/
@Data
//@Data 
@ToString(exclude= {"memPass", "memRegno1", "memRegno2"}) //lombok으로 @ToString추가  =>memPass, memRegno1, memRegno2를 제외한 프로퍼트 전부 toString
@EqualsAndHashCode(of="memId") //lombok으로 Equals ,HashCode추가  =>  memId얘만 같으면 같은 취급 
@NoArgsConstructor  //기본생성자
public class MemberVO implements Serializable {
	
	//생성자 
	public MemberVO(@NotBlank(groups = { Default.class, DeleteGroup.class }) String memId,
			@NotBlank(groups = { Default.class, DeleteGroup.class }) @Size(min = 4, max = 8) String memPass) {
		super();
		this.memId = memId;
		this.memPass = memPass;
	}
	//(groups={InsertGroup.class, UpdateGroup.class})
	//"가입"할때 + "수정"할때 검증하는 그룹 => memId
	//"가입"할때만 검증하는 그룹(groups=InsertGroup.class)   => memBir 
	
	
	//페이징
	private int rnum;
	
	
	
	
	
	//@NotBlank(groups= {Default.class, DeleteGroup.class}, message="아이디는 필수")  // 기본그룹, 기본그룹이면서 insert그룹, 기본그룹이면서 delete그룹 
	@NotBlank(groups= {Default.class, DeleteGroup.class})  
	private String memId;
	@NotBlank(groups= {Default.class, DeleteGroup.class}) //memPass은 비어있을수없음
	@Size(min=4, max=8) //4~8글자 이하
	@JsonIgnore //직렬화할때, 마샬링할때 스킵됨  (주민번호랑 비밀번호는 보이지 말아야 하니까 앞단에 넘겨줄 필요도 없음 )
	private transient String memPass; 
	@NotBlank
	private String memName;
	@JsonIgnore
	private transient String memRegno1;
	@JsonIgnore
	private transient String memRegno2;
	@Pattern(regexp="\\d{4}-\\d{2}-\\d{2}", groups=InsertGroup.class) // 형식제한 => \\d : 숫자 한글자 소문자d =>  TO_DATE(#{memMemorialday, jdbcType=DATE},'YYYY-MM-DD')
	@NotBlank(groups=InsertGroup.class)
	private String memBir; 
	@NotBlank  //비어있을수없음
	private String memZip;
	@NotBlank
	private String memAdd1;
	@NotBlank
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	private String memHp;
	@Email
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	@Pattern(regexp="\\d{4}-\\d{2}-\\d{2}")
	private String memMemorialday;
	//@NotNull //마일리지에는 비어있지 않음을 쓰고싶다면 notBlank가 아니라 notNull을 써야됨 
	@Min(0)//마일리지 최소값 
	private Integer memMileage;
	
	//private String memDelete;
	private boolean memDelete; //타입 바뀌면 getter setter toString 다 바꿔줘야됨 번거로움 => lombok사용해서 쉽게 바꿀수있음 
	//Boolean : 기본값에 null값이 들어있으면 그대로 null로 출렧 
	//boolean : 기본값에 null값이 들어있으면 자동으로false로 바꿔줌 

	
	//구매건수
	private int cartCount; 
	
	//구매기록
	private List<ProdVO> prodList; //has many 관계 (1:N)
	
	
	
	//관리자 권한 , 사용자 권한 
	private String memRole;
	
	private byte[] memImg;
	private MultipartFile memImage; //클라이언트랑 커뮤니케이션

	
	public void setMemImage(MultipartFile memImage) throws IOException {
		if(memImage != null && !memImage.isEmpty()) {
			this.memImage = memImage;
			this.memImg = this.memImage.getBytes();
		}
	}
	public String getBase64MemImg() {
		if(memImg != null)
		 return Base64.getEncoder().encodeToString(memImg);
		else
			return null;
	}
	

}



