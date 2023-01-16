<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <title>MY PAGE</title>
	    <script type="text/javascript">
	    	var page = 6;
	    </script>
    </head>
    <body>
        <aside id="sidebar" class="sidebar">
            <h2>
            	<span></span>
            </h2>
            <ul class="sidebar-nav" id="sidebar-nav">
                <li class="nav-item">
                    <a class="nav-link collapsed" href="/evweb/manager/read.do?read=MYPAGE&manager_id=${user.manager_id}">
                        <i class="bi bi-grid"></i>
                        <span>마이페이지</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link collapsed" href="/evweb/manager/servicelist.do?manager_id=${user.manager_id}">
                        <i class="bi bi-grid"></i>
                        <span>문의내역</span>
                    </a>
                </li>
               
            </ul>
        </aside>
    </body>
</html>