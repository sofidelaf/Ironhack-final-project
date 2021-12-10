export class Stock {

    constructor(
        private _size: string,
        private _units: number
    ){

    }

    public get units(): number {
        return this._units;
    }
    public set units(value: number) {
        this._units = value;
    }
    public get size(): string {
        return this._size;
    }
    public set size(value: string) {
        this._size = value;
    }

}