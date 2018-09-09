package classes;
public class TagBreakdown {
	public static void main(String args[]) {
		String tags = "@badges=subscriber/0,bits/100;color=#B22222;display-name=ashwolf8;emotes=;id=d54dcf26-5864-4b10-97b8-0cd580f79d4c;login=ashwolf8;mod=0;msg-id=resub;msg-param-months=2;msg-param-sub-plan-name=Channel\\sSubscription\\s(pmsproxy);msg-param-sub-plan=Prime;room-id=27974232;subscriber=1;system-msg=ashwolf8\\sjust\\ssubscribed\\swith\\sTwitch\\sPrime.\\sashwolf8\\ssubscribed\\sfor\\s2\\smonths\\sin\\sa\\srow!;tmi-sent-ts=1536179550861;turbo=0;user-id=64186891;user-type= :tmi.twitch.tv USERNOTICE #pmsproxy :I love you and your streams. i hope you have had a good stream so far today";
		String[] split = tags.split(";");
		for(int i = 0; i < split.length; i++) {
			System.out.println(split[i]);
		}
	}
}
