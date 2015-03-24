<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<c:url var="detail" value="/workspace/group/detail.htm" />

<tiles:insertDefinition name="user">
	<tiles:putAttribute name="title">
			Espace de travail
		</tiles:putAttribute>
	<tiles:putAttribute name="content">

		<div id="publications">
			<c:forEach items="${allPublications}" var="p">
				<div class="publication">
					<div class="message">
						<div class="headerMessage">
							<a class="authorPicture" href=""><img class="smallPicture"
								src="${pageContext.request.contextPath}/workspace/avatar.htm?id=${p.author.id}"
								alt="" /></a>
							<p class="author">
								<a href="${pageContext.request.contextPath}/workspace/showProfile.htm?userId=${p.author.id}">${p.author.firstName} ${p.author.lastName}</a>
							</p>
							<p class="datePublication">
								<fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${p.dateP}" />
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
							<p id="numGoodOpinion" class="numGoodOpinion">(${p.goodOpinions.size()})</p>
							<p class="badOpinion" title="Opinion négative"
								onclick="addBadOpinion('${p.id}', '${p.author.id}');">
								<img class="extraSmallPicture"
									src="<c:url value="/images/thumb_down.png"/>" alt="" />
							</p>
							<p id="numBadOpinion" class="numBadOpinion">(${p.badOpinions.size()})</p>
							<p class="numComments"
								onclick="slideToggle('#comments_${p.id}', 'slow');">Commentaires
								(${p.comments.size()})</p>
							<%--<p class="sharePublication">Partager</p>--%>
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
								<%--<form:input id="" class="fileUploadPost" type="file" path="file" />--%>
								<input id="" class="submitPost" type="submit" value="Publier">
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
										<a href="${pageContext.request.contextPath}/workspace/showProfile.htm?userId=${com.author.id}">${com.author.firstName} ${com.author.lastName}</a>
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
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>