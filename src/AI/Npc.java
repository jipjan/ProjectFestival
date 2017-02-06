package AI;

class Npc {
    private State _state;
    private Mood _mood;

    public Npc() {
        _state = State.Neutral;
        _mood = Mood.Neutral;
    }

    public void setMood(Mood m) {
        _mood = m;
    }

    public void setState(State s) {
        _state = s;
    }

    public Mood getMood() {
        return _mood;
    }

    public State getState() {
        return _state;
    }
}
