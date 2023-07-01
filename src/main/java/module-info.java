/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/module-info.java to edit this template
 */

module NostrClient {
    
    requires nostr.ws.handler.command.provider;
    requires nostr.ws.handler;
    requires nostr.util;
    requires nostr.base;
    requires nostr.client;
    requires nostr.crypto;
    requires nostr.event;
    requires nostr.id;
    requires nostr.ws;
    requires nostr.ws.response.handler.provider;
    requires nostr.ws.request.handler.provider;

    requires static lombok;
    requires java.logging;

    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;

    requires org.slf4j;

    requires org.eclipse.jetty.websocket.jetty.client;
    requires org.eclipse.jetty.websocket.jetty.api;
    requires org.eclipse.jetty.websocket.jetty.common;
    requires org.eclipse.jetty.websocket.core.common;
    requires org.eclipse.jetty.websocket.core.client;
    requires org.eclipse.jetty.client;
    requires org.eclipse.jetty.http;
    requires org.eclipse.jetty.util;
    requires org.eclipse.jetty.io;
    requires org.eclipse.jetty.http2.client;
    requires org.eclipse.jetty.http2.common;
    requires org.eclipse.jetty.http2.hpack;
    requires org.eclipse.jetty.alpn.client;
    requires org.eclipse.jetty.http2.http.client.transport;
    requires org.eclipse.jetty.alpn.java.client;
    
    requires org.bouncycastle.provider;

    exports client;
    exports client.provider;
      
    provides nostr.ws.handler.command.spi.ICommandHandler with client.provider.CustomCommandHandler;

}
