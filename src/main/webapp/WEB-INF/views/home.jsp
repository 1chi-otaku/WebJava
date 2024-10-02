
<%@ page contentType="text/html;charset=UTF-8" %>
<h1>Java Web. JSP</h1>


<p>JSP - Java Server Pages - технологія веб-розробки з динамічним формуванням HTML сторінок.
  Анологічно до PHP, ранніх ASP є надбудовою над HTML, що розширує його додаючи:</p>
<ul>
  <li>Вирази</li>
  <li>Змінні</li>
  <li>Алгоритмічні конструкції (умови цикли)</li>
  <li>Взаємодія з іншими файлами-сторінками</li>
</ul>
<p>Основу JSP складають спеціализовані теги &lt;% %> та  &lt;%= %> <br/>
  Тег &lt;% %> включає в себе Java-код, тег &lt;%= %> виводить результат (є скорочення print())</p>
<h2>Вирази</h2>
<br>Вирази частіше за все задаються тегом, що виводить, у якому може бути
довільний вираз (коректний для Java). Виведення результату здійснюється
у тому місці, де знаходиться те <br/>
&lt;%= 2 + 3 %>: = <%= 2 + 3%>
</p>
<h2>Змінні</h2>
<p>
  Змінні, їх оголошення та призначення (без виводу результату)
  оформлюється у блоці &lt;% %>
    <%
        String str = "Sup, world!";
        double[] prices = {10.0,20.0,30.0,40.0};
      %>

<pre>
       &lt;%
         String str = "Sup, world!";
         double[] prices = {10.0,20.0,30.0,40.0};
       %>

       </pre>
</p>

<p>
  Виведення значень змінних - знов тег <br/>
  &lt;%= str %>: &rarr; <%= str%>
</p>

<h2>Алгоритмічні конструкції</h2>
<% for (int i = 0; i < prices.length; i++) {%>
<i> <%= prices[i]%> </i>&emsp;
<% } %>

<pre>
       &lt;% for (int i = 0; i < prices.length; i++) {%>
        &lt;i> &lt;%= prices[i]%> &gt;/i>&amp;emsp;
       &lt;% } %>
    </pre>

<h2>Взаємодія за файлами.</h2>
<jsp:include page="../fragment.jsp"/>


<img src="img/Karasik.jpg" alt="img"/>
<i>Контроль інжекії хешу: <%=request.getAttribute("hash")%></i>

<h2>Домашне завдання. Вивести масив prices в виді таблиці:</h2>

<table border="1">
  <tr>
    <th>Index</th>
    <th>Price</th>
  </tr>
  <% for (int i = 0; i < prices.length; i++) { %>
  <tr>
    <td><%= i %></td>
    <td><%= prices[i] %></td>
  </tr>
  <% } %>
</table>
