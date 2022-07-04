package me.kafein.supercombat.common.tag;

import lombok.Getter;
import lombok.Setter;
import me.kafein.supercombat.common.tag.user.TagUser;

import java.util.UUID;

@Getter @Setter
public class Tag {

    private final TagUser user, otherUser;

    private final TagCause cause;
    private final TagReason reason;

    private final long duration;

    public Tag(TagUser user, TagUser otherUser, TagCause cause, TagReason reason, long duration) {
        this.user = user;
        this.otherUser = otherUser;
        this.cause = cause;
        this.reason = reason;
        this.duration = System.currentTimeMillis() + (duration * 1000);
    }

    public static Tag of(TagUser user, TagUser otherUser, TagCause cause, TagReason reason, long duration) {
        return new Tag(user, otherUser, cause, reason, duration);
    }

    public static Tag of(String userName, UUID userUUID, String otherUserName, UUID otherUserUUID, TagCause cause, TagReason reason, long duration) {
        return of(TagUser.of(userName, userUUID), TagUser.of(otherUserName, otherUserUUID), cause, reason, duration);
    }

    public long getDuration() {
        return (duration - System.currentTimeMillis()) / 1000;
    }

}
