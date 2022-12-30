<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>일련번호</th>
				<th>상품분류</th>
				<th>상품명</th>
				<th>거래처명</th>
				<th>구매가</th>
				<th>판매가</th>
				<th>상품구매자 수</th>
			</tr>
		</thead>
		<tbody>

		</tbody>
		<tfoot>
			<tr>
				<td colspan="7">
					<div id="searchUI">
						<select name="searchType">
							<option value>전체</option>
							<option value="LprodNM">분류명</option>
							<option value="buyerName">거래처명</option>
							<option value="prodName">상품명</option>
						</select> <input type="text" name="searchWord">
					</div>
				</td>
			</tr>
		</tfoot>

	</table>
</body>
</html>