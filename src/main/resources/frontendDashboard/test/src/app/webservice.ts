export class Webservice {
  private _id : BigInt;
  private _url : String;
  private _status : String

  constructor(id:BigInt,url:String,status : String) {
    this._id=id;
    this._url=url;
    this._status=status;
  }

  get id(): BigInt {
    return this._id;
  }

  set id(value: BigInt) {
    this._id = value;
  }

  get url(): String {
    return this._url;
  }

  set url(value: String) {
    this._url = value;
  }

  get status(): String {
    return this._status;
  }

  set status(value: String) {
    this._status = value;
  }
}
