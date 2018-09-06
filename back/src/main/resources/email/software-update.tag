<style>
	h1 {
		font-size: 30px;
		color: #000;
		text-transform: uppercase;
		font-weight: 300;
		text-align: center;
		margin-bottom: 15px;
	}

	table {
		width: 100%;
		table-layout: fixed;
	}

	.tbl-header {
		background-color: rgba(255, 255, 255, 0.3);
	}

	.tbl-content {
		height: 300px;
		overflow-x: auto;
		margin-top: 0px;
		border: 1px solid rgba(255, 255, 255, 0.3);
	}

	th {
		padding: 20px 15px;
		text-align: left;
		font-weight: 500;
		font-size: 12px;
		color: #000;
		text-transform: uppercase;
	}

	td {
		padding: 15px;
		text-align: left;
		vertical-align: middle;
		font-weight: 300;
		font-size: 12px;
		color: #000;
		border-bottom: solid 1px rgba(255, 255, 255, 0.1);
	}

	/* demo styles */
	body {
		background: -webkit-linear-gradient(left, #25c481, #25b7c4);
		background: linear-gradient(to right, #25c481, #25b7c4);
		font-family: 'Roboto', sans-serif;
	}

	section {
		margin: 50px;
	}

	/* follow me template */
	.made-with-love {
		margin-top: 40px;
		padding: 10px;
		clear: left;
		text-align: center;
		font-size: 10px;
		font-family: arial, serif;
		color: #000;
	}

	.made-with-love i {
		font-style: normal;
		color: #F50057;
		font-size: 14px;
		position: relative;
		top: 2px;
	}

	.made-with-love a {
		color: #000;
		text-decoration: none;
	}

	.made-with-love a:hover {
		text-decoration: underline;
	}

	/* for custom scrollbar for webkit browser*/

	::-webkit-scrollbar {
		width: 6px;
	}

	::-webkit-scrollbar-track {
		-webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
	}

	::-webkit-scrollbar-thumb {
		-webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
	}
</style>
<section>
	<h1>老板，您要的软件又更新啦！</h1>
	<div class="tbl-header">
		<table cellpadding="0" cellspacing="0" border="0">
			<thead>
			<tr>
				<th>软件名</th>
				<th>软件版本</th>
				<th>更新日期</th>
				<th>城通网盘链接</th>
				<th>百度网盘链接</th>
				<th>提取码</th>
				<th>采集日期</th>
			</tr>
			</thead>
		</table>
	</div>
	<div class="tbl-content">
		<table cellpadding="0" cellspacing="0" border="0">
			<tbody>
			@for(trData in trDataList){
			<tr>
				<td>${trData.title}</td>
				<td>${trData.version}</td>
				<td>${trData.updateTime,"yyyy-MM-dd"}</td>
				<td>
					@if(trData.ctPanUrl!=null){
					<a target="_blank" href="${trData.ctPanUrl}">城通网盘</a>
					@}
				</td>
				<td>
					@if(trData.bdPanUrl!=null){
					<a target="_blank" href="${trData.bdPanUrl}">百度网盘</a>
					@}
				</td>
				<td>${trData.bdPanCode}</td>
				<td>${trData.spiderTime,"yyyy-MM-dd"}</td>
			</tr>
			@}
			</tbody>
		</table>
	</div>
</section>


<!-- follow me template -->
<div class="made-with-love">
	由 Shiny-Telegram 发送
	<i>♥</i> by
	<a target="_blank" href="http://xkcoding.com">Yangkai.Shen</a>
</div>