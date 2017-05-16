package com.dmd.weixin.mp.message.xml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.dmd.weixin.common.message.MsgType;
import com.dmd.weixin.common.message.XmlMessageHeader;
import com.dmd.weixin.mp.message.Music;

import java.util.Date;

/**
 * Created by exizhai on 11/14/2015.
 */
@JacksonXmlRootElement(localName = "xml")
public class MusicXmlMessage extends XmlMessageHeader {

    @JsonProperty("Music")
    private Music music;

    public MusicXmlMessage() {
        this.msgType = MsgType.music;
        this.setCreateTime(new Date());
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public MusicXmlMessage music(Music music) {
        this.music = music;
        return this;
    }
}