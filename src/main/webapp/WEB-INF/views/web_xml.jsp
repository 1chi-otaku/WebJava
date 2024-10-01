
<%@ page contentType="text/html;charset=UTF-8" %>

<h1>WEB XML PAGE, Ho!</h1>

<p>
  Файл <code>web.xml</code> дозволяє доналаштувати веб-сервер (Toncat або інші)
  під даний проект.
</p>

<h2>Сервелети та маршрутизація</h2>
<p>
  До появи анотацій на кшталт <code>@WebServlet</code> сервлети реєструвались
  у файлі <code>web.xml</code> із зазначенням їх маршрутів (роутінгу)
</p>

<p>
  Для фільтрів <code>web.xml</code> особливо важливий, оскільки гарантує порядок
  виконання фільтрів (якщо їх декілька). В області дії фільтрів поширеною є практика
  шаблонних адрес на кшталт <code>/*</code> або <code>/api/*</code>
</p>
<pre>
  &lt;!-- Реєстрація фільтрів --&gt;
  &lt;filter&gt;
    &lt;filter-name&gt;charsetFilter&lt;/filter-name&gt;
    &lt;filter-class&gt;itstep.learning.filters.CharsetFilter&lt;/filter-class&gt;
  &lt;/filter&gt;
  &lt;filter-mapping&gt;
    &lt;filter-name&gt;charsetFilter&lt;/filter-name&gt;
    &lt;url-pattern&gt;/*&lt;/url-pattern&gt;
  &lt;/filter-mapping&gt;
</pre>

<h2>Сторінки помилок</h2>
<p>
  У <code>web.xml</code> можна закласти адреси для всіх типів помилок:
  як за кодом помилки, так і за типом винятку, що трапляється при обробці.
</p>
<pre>
  &lt;error-page&gt;
    &lt;error-code&gt;404&lt;/error-code&gt;
    &lt;location&gt;/WEB-INF/views/_layout.jsp&lt;/location&gt;
  &lt;/error-page&gt;
</pre>