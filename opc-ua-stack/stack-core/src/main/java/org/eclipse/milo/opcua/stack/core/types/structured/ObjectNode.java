/*
 * Copyright (c) 2017 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

@UaDataType("ObjectNode")
public class ObjectNode extends InstanceNode {

    public static final NodeId TypeId = Identifiers.ObjectNode;
    public static final NodeId BinaryEncodingId = Identifiers.ObjectNode_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ObjectNode_Encoding_DefaultXml;

    protected final UByte _eventNotifier;

    public ObjectNode() {
        super(null, null, null, null, null, null, null, null);
        this._eventNotifier = null;
    }

    public ObjectNode(NodeId _nodeId, NodeClass _nodeClass, QualifiedName _browseName, LocalizedText _displayName, LocalizedText _description, UInteger _writeMask, UInteger _userWriteMask, ReferenceNode[] _references, UByte _eventNotifier) {
        super(_nodeId, _nodeClass, _browseName, _displayName, _description, _writeMask, _userWriteMask, _references);
        this._eventNotifier = _eventNotifier;
    }

    public UByte getEventNotifier() { return _eventNotifier; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("NodeId", _nodeId)
            .add("NodeClass", _nodeClass)
            .add("BrowseName", _browseName)
            .add("DisplayName", _displayName)
            .add("Description", _description)
            .add("WriteMask", _writeMask)
            .add("UserWriteMask", _userWriteMask)
            .add("References", _references)
            .add("EventNotifier", _eventNotifier)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ObjectNode> {
        @Override
        public ObjectNode decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _nodeId = reader.readNodeId();
            NodeClass _nodeClass = NodeClass.from(reader.readInt32());
            QualifiedName _browseName = reader.readQualifiedName();
            LocalizedText _displayName = reader.readLocalizedText();
            LocalizedText _description = reader.readLocalizedText();
            UInteger _writeMask = reader.readUInt32();
            UInteger _userWriteMask = reader.readUInt32();
            ReferenceNode[] _references =
                reader.readArray(
                    () -> (ReferenceNode) context.decode(
                        ReferenceNode.BinaryEncodingId, reader),
                    ReferenceNode.class
                );
            UByte _eventNotifier = reader.readByte();

            return new ObjectNode(_nodeId, _nodeClass, _browseName, _displayName, _description, _writeMask, _userWriteMask, _references, _eventNotifier);
        }

        @Override
        public void encode(SerializationContext context, ObjectNode encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(encodable._nodeId);
            writer.writeInt32(encodable._nodeClass != null ? encodable._nodeClass.getValue() : 0);
            writer.writeQualifiedName(encodable._browseName);
            writer.writeLocalizedText(encodable._displayName);
            writer.writeLocalizedText(encodable._description);
            writer.writeUInt32(encodable._writeMask);
            writer.writeUInt32(encodable._userWriteMask);
            writer.writeArray(
                encodable._references,
                e -> context.encode(ReferenceNode.BinaryEncodingId, e, writer)
            );
            writer.writeByte(encodable._eventNotifier);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ObjectNode> {
        @Override
        public ObjectNode decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _nodeId = reader.readNodeId("NodeId");
            NodeClass _nodeClass = NodeClass.from(reader.readInt32("NodeClass"));
            QualifiedName _browseName = reader.readQualifiedName("BrowseName");
            LocalizedText _displayName = reader.readLocalizedText("DisplayName");
            LocalizedText _description = reader.readLocalizedText("Description");
            UInteger _writeMask = reader.readUInt32("WriteMask");
            UInteger _userWriteMask = reader.readUInt32("UserWriteMask");
            ReferenceNode[] _references =
                reader.readArray(
                    "References",
                    f -> (ReferenceNode) context.decode(
                        ReferenceNode.XmlEncodingId, reader),
                    ReferenceNode.class
                );
            UByte _eventNotifier = reader.readByte("EventNotifier");

            return new ObjectNode(_nodeId, _nodeClass, _browseName, _displayName, _description, _writeMask, _userWriteMask, _references, _eventNotifier);
        }

        @Override
        public void encode(SerializationContext context, ObjectNode encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("NodeId", encodable._nodeId);
            writer.writeInt32("NodeClass", encodable._nodeClass != null ? encodable._nodeClass.getValue() : 0);
            writer.writeQualifiedName("BrowseName", encodable._browseName);
            writer.writeLocalizedText("DisplayName", encodable._displayName);
            writer.writeLocalizedText("Description", encodable._description);
            writer.writeUInt32("WriteMask", encodable._writeMask);
            writer.writeUInt32("UserWriteMask", encodable._userWriteMask);
            writer.writeArray(
                "References",
                encodable._references,
                (f, e) -> context.encode(ReferenceNode.XmlEncodingId, e, writer)
            );
            writer.writeByte("EventNotifier", encodable._eventNotifier);
        }
    }

}
