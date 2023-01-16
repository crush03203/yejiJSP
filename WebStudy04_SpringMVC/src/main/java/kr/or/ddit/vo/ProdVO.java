package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 상품 하나의 정보(분류명, 거래처 정보 포함)를 담기위한 객체 PROD(1) : BUYER(1) -> has a 관계
 *
 */

@Data
@EqualsAndHashCode(of = "prodId")
@ToString(exclude = "prodDetail") // 부하가 걸릴거같은 거는 tostring에서 제외시켜줘야됨
public class ProdVO implements Serializable {

	private int rnum; // 일련번호

	// sql문이 not null로 설정되어있으면 @NotBlank 사용
	@NotBlank(groups = UpdateGroup.class) // InsertGroup.class일때는 prodId 검증 안함/UpdateGroup.class일때만 검증함
	private String prodId;
	@NotBlank(groups = InsertGroup.class)
	private String prodName;

	@NotBlank(groups = InsertGroup.class)
	private String prodLgu;
	private String lprodNm; // 상품명

	@NotBlank
	private String prodBuyer;
	private BuyerVO buyer; // has a 관계 - 거래처명

	@NotNull // integer 타입의 not null은 @NotBlank가 아니라 @NotNull @Min(0)로 표현해야됨
	@Min(0)
	private Integer prodCost;
	@NotNull // integer 타입의 not null은 @NotBlank가 아니라 @NotNull @Min(0)로 표현해야됨
	@Min(0)
	private Integer prodPrice;
	@NotNull // integer 타입의 not null은 @NotBlank가 아니라 @NotNull @Min(0)로 표현해야됨
	@Min(0)
	private Integer prodSale;

	@NotBlank
	private String prodOutline;
	private String prodDetail;

	@NotBlank
	private String prodImg; // prod 테이블 조회용 프로퍼티

	public void setProdImage(MultipartFile prodImage) {
		if (prodImage != null && !prodImage.isEmpty() && prodImage.getContentType().startsWith("image/")) {
			this.prodImage = prodImage;
			this.prodImg = UUID.randomUUID().toString();
		}
	}

	public void saveTo(File saveFolder) throws IOException {
		if (prodImage == null || prodImg == null)
			return;

		File saveFile = new File(saveFolder, prodImg);
		prodImage.transferTo(saveFile);

	}

	private MultipartFile prodImage;

	@NotNull // integer 타입의 not null은 @NotBlank가 아니라 @NotNull @Min(0)로 표현해야됨
	@Min(0)
	private Integer prodTotalstock;
	private String prodInsdate;

	@NotNull // integer 타입의 not null은 @NotBlank가 아니라 @NotNull @Min(0)로 표현해야됨
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
	// private List<MemberVO> memeber; //하나의 상품은 여러명 구매할수있는데 한명이 여러개 구매할수있음
	private Set<MemberVO> memberSet; // 구매자의 정보는 중복될수없다 / has many 관계

	// 상품구매자수
	private int memCount;

}
