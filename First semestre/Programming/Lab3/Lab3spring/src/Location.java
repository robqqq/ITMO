public enum Location {
    FOODROOM{
        @Override
        public String toString() {
            return "food room";
        }
    },
    NONFOODROOM{
        @Override
        public String toString() {
            return "non food room";
        }
    }
}
