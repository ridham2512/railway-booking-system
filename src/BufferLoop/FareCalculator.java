package BufferLoop;

// ==================== Fare calculator ====================
class FareCalculator {
    static float getFare(String travelClass) {
        switch (travelClass) {
            case "Sleeper": return 500f;
            case "1st AC":  return 2000f;
            case "2nd AC":  return 1500f;
            case "3rd AC":  return 1000f;
            default:        return 300f;
        }
    }
}
