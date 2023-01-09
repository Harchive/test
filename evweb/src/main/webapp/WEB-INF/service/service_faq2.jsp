<%@page import="com.project.faq.FaqDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>service</title>
</head>
<body>
<!-- Admin View -->
	<div>
		<h1 class="pagetitle">자주 찾는 도움말</h1>
		<hr>
		<%
		List<FaqDTO> faqlist = (List<FaqDTO>) request.getAttribute("faqlist");
		int size = faqlist.size();
		%>

		<table class="table table-hover">
			<tr bgcolor="#FFFFFF">
				<th></th>
				<th>제목</th>
				<th>내용</th>
				<th>수정</th>
			</tr>
			<%
			for (int i = 0; i < size; i++) {
				FaqDTO faq = faqlist.get(i);
			%>
			<tr>
				<td>▶</td>
				<td><a
					href="/evweb/service/detail?key=id&value=<%=faq.get_id()%>&action=read"><%=faq.gettitle()%></a></td>
				<td><%=faq.getcontent()%></td>
				<td><a href="/evweb/service/detail?key=id&value=<%=faq.get_id()%>&action=update">수정</a></td>
				<%-- <td><a href="mybatisDel.do?_id=<%=faq.get_id()%>">삭제</a></td> --%>
			</tr>
			<%
			}
			%>
			<tr></tr>
			<tr align="center">
				<td colspan="10"><a href="/evweb/faq/paginglist?pageNo=0">1</a>
					<a href="/evweb/faq/paginglist?pageNo=1">2</a> <a
					href="/evweb/faq/paginglist?pageNo=2">3</a> <a
					href="/evweb/faq/paginglist?pageNo=3">4</a></td>
			</tr>
		</table>
		<div class="nav navbar-nav">
			<a href="/evweb/service/faqinsert" style="text-align: right;">글쓰기</a>
		</div>
	</div>
</body>
</html>