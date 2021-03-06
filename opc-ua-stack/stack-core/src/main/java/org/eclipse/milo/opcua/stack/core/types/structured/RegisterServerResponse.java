/*
 * Copyright (c) 2016 Kevin Herron
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
import org.eclipse.milo.opcua.stack.core.serialization.DelegateRegistry;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("RegisterServerResponse")
public class RegisterServerResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.RegisterServerResponse;
    public static final NodeId BinaryEncodingId = Identifiers.RegisterServerResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RegisterServerResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;

    public RegisterServerResponse() {
        this._responseHeader = null;
    }

    public RegisterServerResponse(ResponseHeader _responseHeader) {
        this._responseHeader = _responseHeader;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ResponseHeader", _responseHeader)
            .toString();
    }

    public static void encode(RegisterServerResponse registerServerResponse, UaEncoder encoder) {
        encoder.encodeSerializable("ResponseHeader", registerServerResponse._responseHeader != null ? registerServerResponse._responseHeader : new ResponseHeader());
    }

    public static RegisterServerResponse decode(UaDecoder decoder) {
        ResponseHeader _responseHeader = decoder.decodeSerializable("ResponseHeader", ResponseHeader.class);

        return new RegisterServerResponse(_responseHeader);
    }

    static {
        DelegateRegistry.registerEncoder(RegisterServerResponse::encode, RegisterServerResponse.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(RegisterServerResponse::decode, RegisterServerResponse.class, BinaryEncodingId, XmlEncodingId);
    }

}
