object PlayingField {
    object Size {
        var width = 10
        var height = 10
        fun changeSize(width: Int, height: Int) {
            if (width < 0) this.width = 0
            if (height < 0) this.height = 0
            else {
                Size.width = width
                Size.height = height
            }
        }
    }
}