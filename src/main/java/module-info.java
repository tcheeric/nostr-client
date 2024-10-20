/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/module-info.java to edit this template
 */

import client.provider.EventCustomHandler;
import client.provider.NoticeCustomHandler;
import client.provider.OKCustomHandler;

module NostrClient {

    requires nostr.api;
    requires nostr.util;
    requires nostr.base;
    requires nostr.event;
    requires nostr.id;
    requires nostr.client;
    requires nostr.command.handler;
    requires nostr.context;
    requires nostr.context.impl;

    requires static lombok;
    requires java.logging;

    requires org.bouncycastle.provider;

    exports client;
    exports client.provider;
      
    provides nostr.command.CommandHandler with EventCustomHandler, OKCustomHandler, NoticeCustomHandler;

}
