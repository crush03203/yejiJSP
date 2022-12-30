package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.InsertGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * VO(Value Object), DTO(Data Transfer Object), Java Bean, Model
 *
 * JavaBean 규약 1. 값을 담을 수 있는 property 정의 2. property 캡슐화 3. 캡슐화된 프로퍼티에 접근할 수 있는
 * 인터페이스 제공 getter/setter get[set] + 프로퍼티명의 첫문자를 대문자로 -> camel case 4. 객체의 상태 비교
 * 방법 제공 : equals ==, equals(주소를 비교할지 뭘 비교할지 몰라) 차이점 5. 객체의 상태 확인 방법 제공 :
 * toString 6. 객체 직렬화 가능 (transient를 추가하면 직렬화 하지 않음, @JsonIgnore 추가하면 마샬링 하지 않음)
 *
 * 회원 관리를 위한 Doamin Layer : 한 사람의 회원 정보(구매기록 포함)를 담기 위한 객체. MEMGER(1) : PROD(N)
 * -> HAS MANY
 *
 * **데이터 매퍼나 ORM을 이용한 테이블 조인 방법 ex) 회원 정보 상세 조회시 구매 상품 기록을 함께 조회함 1. 대상 테이블 선택
 * MEMBER CART(CART_MEMBER, CART_PROD)), PROD 2. 각 테이블로부터 데이터를 바인딩할 VO 설계
 * MemberVO, ProdVO 3. 각테이블의 relation을 VO 사이에 has 관계로 반영 1(main) : N -> has
 * many, MemberVO has many ProdVO(collection) 1(main) : 1 -> has a , Member VO
 * has a BuyerVO 4. resultType 대신 resultMap으로 바인딩 설정 has many : collection has a
 * : association
 *
 * 
 * 
 * 
 */
//@Getter
//@Setter
@ToString(exclude = { "memPass,memRegno1,memRegno2" })
@EqualsAndHashCode(of = "memId")
@Data // 위에 주석 처리를 다 해준다
@NoArgsConstructor
public class MemberVO implements Serializable {

	public MemberVO(@NotBlank(groups = { Default.class, DeleteGroup.class }) String memId,
			@NotBlank(groups = { Default.class, DeleteGroup.class }) @Size(min = 4, max = 8) String memPass) {
		super();
		this.memId = memId;
		this.memPass = memPass;
	}

	private int rnum;

	@NotBlank(groups = { Default.class, DeleteGroup.class })
	private String memId;
	@JsonIgnore // 마샬링에서 제외할 때
	@NotBlank(groups = { Default.class, DeleteGroup.class })
	@Size(min = 4, max = 8) // 비밀번호 4글자에서 최대 8글자 설정
	private transient String memPass;
	@NotBlank
	private String memName;
	@JsonIgnore
	private transient String memRegno1;
	@JsonIgnore
	private transient String memRegno2;
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", groups = InsertGroup.class)
	@NotBlank(groups = InsertGroup.class)
	private String memBir;
	@NotBlank
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
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
	private String memMemorialday;
	@Min(0)
	private Integer memMileage;
	private boolean memDelete;

	// 구매건수
	private int cartCount;

//   구매기록
	private List<ProdVO> prodList; // has many 관계(1:N)

}