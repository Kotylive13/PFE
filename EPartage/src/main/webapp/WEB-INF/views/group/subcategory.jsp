<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<c:url var="detail" value="/workspace/group/subcategory/detail.htm" />

<tiles:insertDefinition name="user">
	<tiles:putAttribute name="title">
			${group.name} - ${subcategory.idSubcategory.subcategory}
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
				<li><a
					href="${pageContext.request.contextPath}/workspace/group/detail.htm?nameG=${groupsUrl[group.name]}">Description</a>
				</li>
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

		<h1>${subcategory.idSubcategory.category}</h1>
		<h2>${subcategory.idSubcategory.subcategory}</h2>

		<c:choose>
			<c:when test="${not empty type}">
				<div class="information">
					<p>Pour accèder au contenu du groupe, veuillez faire une
						demande d'adhésion auprès de l'administrateur.</p>
				</div>
			</c:when>
			<c:otherwise>

				<div class="postPublication">
					<form:form id="postPublicationForm" modelAttribute="publication"
						method="post" enctype="multipart/form-data"
						action="${pageContext.request.contextPath}/publication/edit.htm?nameS=${urlParams[subcategory.idSubcategory.subcategory]}&nameG=${urlParams[subcategory.idSubcategory.group]}&nameC=${urlParams[subcategory.idSubcategory.category]}">
						<div class="contentPost">
							<form:input name="titlePostPublication" class="titlePost"
								type="text" placeholder="Titre" path="title" />
							<%--<form:errors path="title" cssClass="error" />--%>
							<form:textarea path="content" name="messagePostPublication"
								class="messagePost" placeholder="Exprimez-vous" />
							<form:errors path="content" cssClass="error" />
						</div>
						<div class="footerPost">
							<form:input class="fileUploadPost" type="file" name="file"
								path="file" />
							<input class="submitPost" type="submit" value="Publier">
						</div>
					</form:form>
				</div>

				<div id="publications">
					<c:choose>
						<c:when test="${subcategory.publications.size() > 0}">
							<c:set var="size" value="${subcategory.publications.size()-1}" />
							<c:forEach var="i" begin="0" end="${size}" step="1"
								varStatus="loop">
								<c:set var="p" value="${subcategory.publications.get(size-i)}" />
								<div class="publication">
									<div class="message">
										<div class="headerMessage">
											<a class="authorPicture" href=""><img
												class="smallPicture"
												src="${pageContext.request.contextPath}/workspace/avatar.htm?id=${p.author.id}"
												alt="" /></a>
											<p class="author">
												<a href="${pageContext.request.contextPath}/workspace/showProfile.htm?userId=${p.author.id}">${p.author.firstName} ${p.author.lastName}</a>
											</p>
											<p class="datePublication">
												<fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss"
													value="${p.dateP}" />
											<P>
											<p class="optionsPublication">
												<%--<img class="veryExtraSmallPicture"
											src="<c:url value="/images/cross.png"/>" alt="" />--%>
											</p>
										</div>
										<div class="contentMessage">
											<p>${p.title}</p>
											<p>${p.content}</p>

											<p>
												<a
													href="${pageContext.request.contextPath}/publication/file.htm?pub=${p.id}&id=${p.files[0].idPublicationFile.id}"
													target="_blank">${p.files[0].title}</a>
											</p>
										</div>
										<div class="footerMessage">
											<p class="goodOpinion" title="Opinion positive"
												onclick="addGoodOpinion('${p.id}', '${p.author.id}');">
												
												<img class="extraSmallPicture"
													src="<c:url value="/images/thumb_up.png"/>" alt="" />
											</p>
											<p class="numGoodOpinion">(${p.goodOpinions.size()})</p>
											<p class="badOpinion" title="Opinion négative"
												onclick="addBadOpinion('${p.id}', '${p.author.id}');">
												<img class="extraSmallPicture"
													src="<c:url value="/images/thumb_down.png"/>" alt="" />
											</p>
											<p class="numBadOpinion">(${p.badOpinions.size()})</p>
											<p class="numComments"
												onclick="slideToggle('#comments_${p.id}', 'slow');">Commentaires
												(${p.comments.size()})</p>
											<%--<p class="sharePublication">Partager</p>--%>
											<p class="commentPublication"
												onclick="slideToggle('#postComment_${p.id}', 'slow');">Commenter</p>
										</div>
									</div>
									<div id="postComment_${p.id}" class="postComment">
										<form:form id="postPublicationForm" modelAttribute="comment"
											method="post" enctype="multipart/form-data"
											action="${pageContext.request.contextPath}/publication/comment/edit.htm?id_pub=${p.id}">
											<div class="contentPost">
												<form:textarea id="" class="messagePost" placeholder="Exprimez-vous" path="content" />
											</div>
											<div class="footerPost">
												<form:input id="" class="fileUploadPost" type="file" path="file" />
												<input id="" class="submitPost" type="submit" value="Publier">
											</div>
										</form:form>
									</div>
									<div id="comments_${p.id}" class="comments">
										<c:forEach items="${p.comments}" var="com">
											<div class="comment">
												<div class="headerComment">
													<a class="authorPicture" href=""><img
														class="smallPicture"
														src="${pageContext.request.contextPath}/workspace/avatar.htm?id=${com.author.id}"
														alt="" /></a>
													<p class="author">
														<a href="${pageContext.request.contextPath}/workspace/showProfile.htm?userId=${com.author.id}">${com.author.firstName}
															${com.author.lastName}</a>
													</p>
													<p class="datePublication">
														<fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss"
															value="${com.dateC}" />
													<P>
														<%--<a class="optionsPublication" href=""><img
													class="veryExtraSmallPicture"
													src="<c:url value="/images/cross.png"/>" alt="" /></a>--%>
												</div>
												<div class="contentComment">
													<p>${com.content}</p>
													<p>
														<a
															href="${pageContext.request.contextPath}/publication/comment/file.htm?pub=${p.id}&com=${com.idComment.num}&id=${com.files[0].idCommentFile.id}"
															target="_blank">${com.files[0].title}</a>
													</p>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div class="information">
								<p>Aucune publication</p>
							</div>
						</c:otherwise>
					</c:choose>
				</div>

			</c:otherwise>
		</c:choose>
	</tiles:putAttribute>
</tiles:insertDefinition>
