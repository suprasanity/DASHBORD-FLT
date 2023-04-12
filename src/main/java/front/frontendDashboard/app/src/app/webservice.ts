export class Webservice {
  private _id : number;
  private _url : string;
  private _status : string

  constructor(id:number,url:string,status : string) {
    this._id=id;
    this._url=url;
    this._status=status;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get url(): string {
    return this._url;
  }

  set url(value: string) {
    this._url = value;
  }

  get status(): string {
    return this._status;
  }

  set status(value: string) {
    this._status = value;
  }
}
