package com.ailk.eaap.op2.sso.main.util;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;


public class TreeNodeManage {
	private Map nodeMap = new HashMap();

	private List nodeList;

	public TreeNodeManage(List noteList) {
		this.nodeList = noteList;
		initNodeMap();
	}

	public List getSubNodesByParentNode(String nodeId) {
		return (List) nodeMap.get(nodeId);
	}

	public List getAllNodesList() {
		return this.nodeList;
	}

	private void initNodeMap() {
		if (nodeList != null && nodeList.size() > 0) {
			for (Object aNodeList : nodeList) {
				TreeNode treeNode = (TreeNode) aNodeList;
				String parentId = treeNode.getParentid();
				if (parentId != null && !parentId.equals("")) {
					List subList = (List) nodeMap.get(parentId);
					if (subList == null) {
						subList = new ArrayList();
					}
					subList.add(treeNode);
					nodeMap.put(parentId, subList);
				}
			}
		}
	}
}
