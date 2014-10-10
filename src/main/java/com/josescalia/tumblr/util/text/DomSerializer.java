package com.josescalia.tumblr.util;

import org.w3c.dom.*;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Josescalia
 * Date: 12/7/12
 * Time: 10:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class DomSerializer {
    private String indent;
    private String lineSeparator;

    public DomSerializer() {
        indent = "     ";
        lineSeparator = "\n";
    }

    public void setIndent(String indent) {
        this.indent = indent;
    }

    public void setLineSeparator(String lineSeparator) {
        this.lineSeparator = lineSeparator;
    }

    public String serialize(Document doc) {
        try {
            StringBuffer buffer = new StringBuffer();
            // Start serialization recursion with no indenting
            serializeNode(doc, buffer, "");
            return buffer.toString();
        } catch (Exception ioe) {
            System.out.println("Something wrong");
            return null;
        }
    }

    public void serializeNode(Node node, StringBuffer buffer,
                              String indentLevel) {
        // Determine action based on node type
        switch (node.getNodeType()) {
            case Node.DOCUMENT_NODE:
                //buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                buffer.append("<?xml version=\"1.0\"?>");
                buffer.append(lineSeparator);
                // recurse on each child
                NodeList nodes = node.getChildNodes();
                if (nodes != null) {
                    for (int i = 0; i < nodes.getLength(); i++) {
                        serializeNode(nodes.item(i), buffer, "");
                    }
                }
                break;
            case Node.ELEMENT_NODE:
                String name = node.getNodeName();
                buffer.append(indentLevel + "<" + name);
                NamedNodeMap attributes = node.getAttributes();
                for (int i = 0; i < attributes.getLength(); i++) {
                    Node current = attributes.item(i);
                    buffer.append(" " + current.getNodeName() +
                            "=\"" + current.getNodeValue() +
                            "\"");
                }
                buffer.append(">");
                // recurse on each child
                NodeList children = node.getChildNodes();
                if (children != null) {
                    if ((children.item(0) != null) &&
                            (children.item(0).getNodeType() ==
                                    Node.ELEMENT_NODE)) {
                        buffer.append(lineSeparator);
                    }
                    for (int i = 0; i < children.getLength(); i++) {
                        serializeNode(children.item(i), buffer,
                                indentLevel + indent);
                    }
                    if ((children.item(0) != null) &&
                            (children.item(children.getLength() - 1)
                                    .getNodeType() ==
                                    Node.ELEMENT_NODE)) {
                        buffer.append(indentLevel);
                    }
                }
                buffer.append("</" + name + ">");
                buffer.append(lineSeparator);
                break;
            case Node.TEXT_NODE:
                buffer.append(node.getNodeValue());
                break;
            case Node.CDATA_SECTION_NODE:
                buffer.append("<![CDATA[" +
                        node.getNodeValue() + "]]>");
                break;
            case Node.COMMENT_NODE:
                buffer.append(indentLevel + "<!-- " +
                        node.getNodeValue() + " -->");
                buffer.append(lineSeparator);
                break;
            case Node.PROCESSING_INSTRUCTION_NODE:
                buffer.append("<?" + node.getNodeName() +
                        " " + node.getNodeValue() +
                        "?>");
                buffer.append(lineSeparator);
                break;
            case Node.ENTITY_REFERENCE_NODE:
                buffer.append("&" + node.getNodeName() + ";");
                break;
            case Node.DOCUMENT_TYPE_NODE:
                DocumentType docType = (DocumentType) node;
                buffer.append("<!DOCTYPE " + docType.getName());
                if (docType.getPublicId() != null) {
                    buffer.append(" PUBLIC \"" +
                            docType.getPublicId() + "\" ");
                } else {
                    buffer.append(" SYSTEM ");
                }
                buffer.append("\"" + docType.getSystemId() + "\">");
                buffer.append(lineSeparator);
                break;
        }
    }
}
