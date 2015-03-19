<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<c:url var="detail" value="/workspace/group/subcategory/detail.htm" />

<tiles:insertDefinition name="user">
	<tiles:putAttribute name="title">
			${group.name}
		</tiles:putAttribute>
	<tiles:putAttribute name="content">
		<div class="title">
			<img class="largePicture"
				src="${pageContext.request.contextPath}/workspace/group/avatar.htm?nameG=${groupsUrl[group.name]}"
				alt="" />
			<p>${group.name}</p>
		</div>

		<div class="menuBar">
			<ul class="menu">
				<c:set var="nameG" value="${urlParams[group.name]}" />
				<c:forEach items="${group.categories}" var="cat">
					<li><a href="#">${cat.idCategory.name}</a> <c:set var="nameC"
							value="${urlParams[cat.idCategory.name]}" />
						<ul>
							<c:forEach items="${cat.subcategories}" var="sub">
								<c:set var="nameS"
									value="${urlParams[sub.idSubcategory.subcategory]}" />
								<li><a
									href="${detail}?nameG=${nameG}&nameC=${nameC}&nameS=${nameS}">${sub.idSubcategory.subcategory}</a></li>
							</c:forEach>
						</ul></li>
				</c:forEach>
			</ul>
		</div>

		<a
			href="/EPartage/publication/edit.htm?nameG=${param['nameG']}&nameC=${param['nameC']}&nameS=${param['nameS']}">
			Ajouter une publication</a>

		<div class="postPublication">
			<form id="postPublicationForm" method="post" action="">
				<div class="contentPost">
					<input name="titlePostPublication" class="titlePost" type="text"
						placeholder="Titre" />
					<textarea name="messagePostPublication" class="messagePost"
						placeholder="Exprimez-vous"></textarea>
				</div>
				<div class="footerPost">
					<input class="fileUploadPost" type="file" /> <input
						class="submitPost" type="submit" value="Publier">
				</div>
			</form>
		</div>

		<div id="publications">
			<c:forEach items="${subcategory.publications}" var="p">
				<div class="publication">
					<div class="message">
						<div class="headerMessage">
							<a class="authorPicture" href=""><img class="smallPicture"
								src="${pageContext.request.contextPath}/workspace/avatar.htm?id=${p.author.id}"
								alt="" /></a>
							<p class="author">
								<a href="">${p.author.firstName} ${p.author.lastName}</a>
							</p>
							<p class="datePublication">
								<fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${p.dateP}" />
							<P>
							<p class="optionsPublication">
								<!--<img class="veryExtraSmallPicture"
									src="<c:url value="/images/cross.png"/>" alt="" />-->
							</p>
						</div>
						<div class="contentMessage">
							<p>${p.title}</p>
							<p>${p.content}</p>
							
							<p>
								<a href="${pageContext.request.contextPath}/publication/file.htm?pub=${p.id}&id=${p.files[0].idPublicationFile.id}"  target="_blank">${p.files[0].title}</a>
							</p>
						</div>
						<div class="footerMessage">
							<p class="goodOpinion" title="Opinion positive">
								<img class="extraSmallPicture"
									src="<c:url value="/images/thumb_up.png"/>" alt="" />
							</p>
							<p class="numGoodOpinion">()</p>
							<p class="badOpinion" title="Opinion négative">
								<img class="extraSmallPicture"
									src="<c:url value="/images/thumb_down.png"/>" alt="" />
							</p>
							<p class="numBadOpinion">()</p>
							<p class="numComments"
								onclick="slideToggle('#comments_${p.id}', 'slow');">Commentaires
								(${p.comments.size()})</p>
							<p class="sharePublication">Partager</p>
							<p class="commentPublication"
								onclick="slideToggle('#postComment_${p.id}', 'slow');">Commenter</p>
						</div>
					</div>
					<div id="postComment_${p.id}" class="postComment">
						<form>
							<div class="contentPost">
								<textarea id="" class="messagePost" placeholder="Exprimez-vous"></textarea>
							</div>
							<div class="footerPost">
								<input id="" class="fileUploadPost" type="file" /> <input id=""
									class="submitPost" type="submit" value="Publier">
							</div>
						</form>
					</div>
					<div id="comments_${p.id}" class="comments">
						<c:forEach items="${p.comments}" var="com">
							<div class="comment">
								<div class="headerComment">
									<a class="authorPicture" href=""><img class="smallPicture"
										src="${pageContext.request.contextPath}/workspace/avatar.htm?id=${com.author.id}"
										alt="" /></a>
									<p class="author">
										<a href="">${com.author.firstName} ${com.author.lastName}</a>
									</p>
									<p class="datePublication">
										<fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss"
											value="${com.dateC}" />
									<P>
										<!--<a class="optionsPublication" href=""><img
											class="veryExtraSmallPicture"
											src="<c:url value="/images/cross.png"/>" alt="" /></a>-->
								</div>
								<div class="contentComment">
									<p>${com.content}</p>
									<p>
										<a href="${pageContext.request.contextPath}/publication/comment/file.htm?pub=${p.id}&com=${com.idComment.num}&id=${com.files[0].idCommentFile.id}"  target="_blank">${com.files[0].title}</a>
									</p>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</c:forEach>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>