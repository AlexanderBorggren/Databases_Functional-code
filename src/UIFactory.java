public class UIFactory {
    public UI createInterface(String input) {
        if (input.equals("G")) {
            return new GInterface();
        } else if (input.equals("VG")) {
            return new VGInterface();
        }
        else {
            throw new IllegalArgumentException("Ogiltig inmatning");
        }

    }
}
