new ViewInfo({
    el: '#app',
    data: {
        src: 'MyColl6.jpg',
        rows: 3,
        cols: 3,
        size: 100,
        pieces: [],
        activePiece: null
    },
    methods: {
        style(id) {
            const { size, rows, cols, src } = this

            return {
                width: `${size}px`,
                height: `${size}px`,
                'background-image': `url(${src})`,
                'background-size': `${cols * size}px ${rows * size}px`,
                'background-position': `-${(id % cols) * size}px -${(id / cols | 0) * size}px`
            }
        },
        setActivePiece(x, y, e) {
            if (!e.buttons) {
                return
            }

            const { activePiece, pieces } = this

            if (activePiece && (activePiece.x !== x || activePiece.y !== y)) {
                const t = pieces[activePiece.y][activePiece.x]
                this.$set(pieces[activePiece.y], activePiece.x, pieces[y][x])
                this.$set(pieces[y], x, t)
            }

            this.activePiece = { x, y }
        },
        delActivePiece() {
            this.activePiece = null
        }
    },
    created() {
        const { rows, cols } = this

        let ids = [...Array(rows * cols)]
            .map((n, i) => i)

        this.pieces = [...Array(rows)]
            .map(() => [...Array(cols)].map(() => ids.shift()))
    }
})
