package porebska.filmsmatcher.model;

public enum Status {
    SEEN,
    WANT_TO_WATCH,
    DO_NOT_WANT_TO_WATCH;

    public static Status intToStatus(int p) {
        switch (p) {
            case 0:
                return SEEN;
            case 1:
                return WANT_TO_WATCH;
            case 2:
                return DO_NOT_WANT_TO_WATCH;
        }
        return null;
    }
}
