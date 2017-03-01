package com.ailk.eaap.op2.sso.main.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 
 * @author Administrator
 *
 */
public class TreeTag extends TagSupport {
	private static final String DEFAULT_PATH = "/images/dhtmlxtree/";

	private static final String IMG_PLUS_1 = "plus3.gif";

	private static final String IMG_PLUS_2 = "plus2.gif";

	private static final String IMG_MINUS_1 = "minus3.gif";

	private static final String IMG_MINUS_2 = "minus2.gif";

	private static final String IMG_MIDDLE_LINE = "line3.gif";

	private static final String IMG_LINE = "line1.gif";

	private static final String IMG_END_LINE = "line2.gif";

	private static final String IMG_FOLDER_CLOSE = "folderClosed.gif";

	private static final String IMG_FOLDER_OPEN = "folderOpen.gif";

	private String rootId;

	private String checkBox;

	private String clickCheckBoxFunction;

	private String clickTitleFunction;

	private String imgFolderClose;

	private String imgFolderOpen;

	private String imgPath;

	private TreeNodeManage treeNodeManage;

	public void setRootId(String value) {
		this.rootId = value;
	}

	public void setCheckBox(String value) {
		this.checkBox = value;
	}

	public void setClickCheckBoxFunction(String clickCheckBoxFunction) {
		this.clickCheckBoxFunction = clickCheckBoxFunction;
	}

	public void setClickTitleFunction(String clickTitleFunction) {
		this.clickTitleFunction = clickTitleFunction;
	}

	public void setImgFolderClose(String value) {
		this.imgFolderClose = value;
	}

	public void setImgFolderOpen(String value) {
		this.imgFolderOpen = value;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		if (imgPath == null || imgPath.trim().equals("")) {
			this.imgPath = DEFAULT_PATH;
		} else {
			imgPath = imgPath.trim();
			if (imgPath.endsWith("/")) {
				this.imgPath = imgPath;
			} else {
				this.imgPath = imgPath + "/";
			}
		}
	}

	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		List treeNodeList = (List) request.getAttribute("treeNodeList");
		if (treeNodeList == null || treeNodeList.isEmpty()) {
			try {
				out.println("不存在树结点!");
			} catch (java.io.IOException e) {
				e.printStackTrace();
			}
		} else {
			String contextPath = request.getContextPath();
			if (imgFolderClose == null || imgFolderClose.equals("")) {
				imgFolderClose = IMG_FOLDER_CLOSE;
			}
			if (imgFolderOpen == null || imgFolderOpen.equals("")) {
				imgFolderOpen = IMG_FOLDER_OPEN;
			}
			try {
				out.println("<script type='text/javascript'>");
				out.println(createClickNodeFunction(contextPath));
				out.println("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			treeNodeManage = new TreeNodeManage(treeNodeList);
			writeTreeNode(out, rootId, false, contextPath);
		}
		return SKIP_BODY;
	}

	private void writeTreeNode(JspWriter out, String nodeId, boolean display,
			String contextPath) {
		List list = treeNodeManage.getSubNodesByParentNode(nodeId);
		try {
			if (list != null && list.size() > 0) {
				out
						.println("<table border='0' cellspacing='0' cellpadding='0' width='100%'>");
				for (int i = 0; i < list.size(); i++) {
					TreeNode treeNode = (TreeNode) list.get(i);
					String noteId = treeNode.getNoteid();
					// 子结点列表
					List subList = treeNodeManage
							.getSubNodesByParentNode(noteId);
					String bgStyle = "";
					String last_flag = "0";
					String img_flag_1 = "";
					String click_string = "";
					int childNum = 0;
					boolean last = ((i + 1) == list.size());
					if (subList != null && subList.size() > 0) {
						if (last) {
							img_flag_1 = contextPath + this.imgPath
									+ IMG_PLUS_2;
							last_flag = " last='1' ";
						} else {
							img_flag_1 = contextPath + this.imgPath
									+ IMG_PLUS_1;
							last_flag = " last='0' ";
							bgStyle = "style='background-image: url("
									+ contextPath + this.imgPath + IMG_LINE
									+ ");'";
						}
						click_string = " onclick=\"clickNode(this,\'"
								+ noteId
								+ "\')\" onmouseover='this.style.cursor=\"hand\"' ";
						childNum = subList.size();
					} else {
						if (last) {
							img_flag_1 = contextPath + this.imgPath
									+ IMG_END_LINE;
						} else {
							img_flag_1 = contextPath + this.imgPath
									+ IMG_MIDDLE_LINE;
							bgStyle = "style='background-image: url("
									+ contextPath + this.imgPath + IMG_LINE
									+ ");'";
						}
					}
					String open_state_id = "open_" + noteId;
					String img_id = "img_" + noteId;
					out.println("<tr><td width='13px' valign='top' " + bgStyle
							+ ">");
					out.println("<input type='hidden' id='" + open_state_id
							+ "' value='1'>");
					out.println("<img id='" + img_id + "' src='" + img_flag_1
							+ "' " + click_string + last_flag + " childNum='"
							+ childNum + "'>");
					out.println("</td><td><nobr>");
					String checkCode = "";
					if ("y".equalsIgnoreCase(checkBox)) {
						String checkBoxId = "cb_" + noteId;
						checkCode = "<input type='checkbox' onmouseover='this.style.cursor=\"hand\"' name='noteid' parentId='"
								+ treeNode.getParentid()
								+ "' value='"
								+ noteId
								+ "' id='"
								+ checkBoxId
								+ "' layer='"
								+ treeNode.getLayer()
								+ "' onclick='"
								+ this.clickCheckBoxFunction
								+ "'"
								+ " childNum='" + childNum + "'>";
					}
					String img_folder = "folder_" + noteId;
					out.println(checkCode + "<img src='" + contextPath
							+ imgFolderClose + "' id='" + img_folder + "'>");
					out
							.println("<span style='cursor:hand' onmouseover='this.style.color=\"#FF0000\"' value='"
									+ noteId
									+ "'"
									+ "onclick='"
									+ this.clickTitleFunction
									+ "' onmouseout='this.style.color=\"#000000\"'>");
					out.println(treeNode.getNotename());
					out.println("(" + treeNode.getChildrennum() + ")");
					out.println("</span>");
					out.println("</nobr>");
					String divId = "div_" + noteId;
					if (display) {
						out.println("<div id='" + divId + "'>");
					} else {
						out.println("<div id='" + divId
								+ "' style='display:none'>");
					}

					if (subList != null && subList.size() > 0) {
						writeTreeNode(out, noteId, false, contextPath);
					}
					out.println("</div></td></tr>");
				}
				out.println("</table>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String createClickNodeFunction(String contextPath) {
		StringBuffer sb = new StringBuffer();
		sb.append("function clickNode(obj,id){");
		sb.append("\ntry{");
		sb.append("\n  if(parseInt(obj.childNum)<=0){");
		sb.append("\n     return;");
		sb.append("\n   }");
		sb.append("\n}catch(e){return;}");
		sb.append("\nvar div_id = \"div_\" + id;");
		sb.append("\nvar folder_id = \"folder_\" + id;");
		sb.append("\nvar open_id = \"open_\" + id;");
		sb.append("\nvar divObj = document.getElementById(div_id);");
		sb.append("\nvar folderObj = document.getElementById(folder_id);");
		sb.append("\nvar flagObj = document.getElementById(open_id);");
		sb.append("\nif (flagObj.value == '0') {");
		sb.append("\n  if (obj.last == \"1\") {");
		sb.append("\n     obj.src = '" + contextPath + this.imgPath
				+ IMG_PLUS_2 + "';");
		sb.append("\n  } else {");
		sb.append("\n     obj.src = '" + contextPath + this.imgPath
				+ IMG_PLUS_1 + "';");
		sb.append("\n  }");
		sb.append("\n  flagObj.value = '1';");
		sb.append("\n  divObj.style.display = 'none';");
		sb.append("\n  folderObj.src = '" + contextPath + this.imgPath
				+ IMG_FOLDER_CLOSE + "';");
		sb.append("\n} else {");
		sb.append("\n  if (obj.last == \"1\") {");
		sb.append("\n     obj.src = '" + contextPath + this.imgPath
				+ IMG_MINUS_2 + "';");
		sb.append("\n  } else {");
		sb.append("\n     obj.src = '" + contextPath + this.imgPath
				+ IMG_MINUS_1 + "';");
		sb.append("\n  }");
		sb.append("\n  flagObj.value = '0';");
		sb.append("\n  folderObj.src = '" + contextPath + this.imgPath
				+ IMG_FOLDER_OPEN + "';");
		sb.append("\n  divObj.style.display = ''; ");
		sb.append("\n}\n}");
		return sb.toString();
	}
}