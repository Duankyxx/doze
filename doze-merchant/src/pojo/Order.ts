export default interface Order{
    id: number,
    seatNumber: number
    time: string,
    payment: number,
    remarks: string | null,
    uid: number,
    receipts: Array<Receipt>,
}

interface Receipt{
    id: number,
    cookName: string,
    quantity: number,
    price: number,
    serialNumber: number
}