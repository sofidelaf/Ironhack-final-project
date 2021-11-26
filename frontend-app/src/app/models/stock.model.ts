export class Stock {

    constructor(
        private _articlesize: string,
        private _units: number
    ){

    }

    public get units(): number {
        return this._units;
    }
    public set units(value: number) {
        this._units = value;
    }
    public get articlesize(): string {
        return this._articlesize;
    }
    public set articlesize(value: string) {
        this._articlesize = value;
    }

}