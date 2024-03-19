/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/module-info.java to edit this template
 */

module NostrClient {

    requires nostr.api;
    requires nostr.ws.handler;
    requires nostr.util;
    requires nostr.base;
    requires nostr.event;
    requires nostr.id;

    requires static lombok;
    requires java.logging;

    requires org.bouncycastle.provider;

    exports client;
    exports client.provider;
      
    provides nostr.ws.handler.command.spi.ICommandHandler with client.provider.CustomCommandHandler;

}
