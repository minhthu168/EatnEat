package fpt.aptech.eatneatapp.session;

public class Session {
        static Object Session;

        public static Object getSession() {
            return Session;
        }

        public static void setSession(Object session) {
            Session = session;
        }
}
