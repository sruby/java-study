import type { NextApiRequest, NextApiResponse } from 'next';

export default async function handler(req: NextApiRequest, res: NextApiResponse) {
    try {
        const response = await fetch('https://llm.hibor.com.cn:8001/hibor/api/yanbao_qa/chat_stream', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify({
                question: "那比亚迪的呢",
                token: "84ffb66b11e9b0a37a239758e6ea181c",
                history: [
                    { role: "user", content: "特斯拉的销量" },
                    { role: "assistant", content: "特斯拉以88.8万辆的销量位列2023年上半年的销量排行第二" }
                ],
                date_filter: "range",
                start_date: "2023-09-11",
                end_date: "2023-11-11"
            })
        });

        if (response.ok) {
            res.status(200);
            response.body.pipe(res);
        } else {
            // Handle the error response here
            res.status(response.status);
            res.send(await response.text());
        }
    } catch (error) {
        res.status(500).send(error.message);
    }
}