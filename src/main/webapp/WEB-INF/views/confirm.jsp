<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Memory-log 이메일 인증</title>
    </head>

    <script>
        const loaded = () => {
            if (${isConfirm}) {
                alert("인증 성공")
            } else {
                alert("인증 실패")
            }
            self.opener = self
            window.close()
        }
    </script>
    <body onload="loaded()">
    </body>
</html>