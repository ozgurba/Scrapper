import java.util.List;

import org.jsoup.nodes.Node;
import org.jsoup.select.NodeVisitor;

public  class MyNodeVisitor implements NodeVisitor {

    private List<String> childList;

    public MyNodeVisitor(List<String> childList) {
        if (childList == null) {
            throw new NullPointerException("child cannot be null.");
        }

        this.childList = childList;
    }

    
	@Override
	public void head(Node node, int depth) {
		if (node.childNodeSize() == 0) {
            childList.add(node.toString());
        }
	}

	@Override
	public void tail(Node node, int depth) {
		// TODO Auto-generated method stub
		
	}
}