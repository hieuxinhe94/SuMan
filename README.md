# SuMan
Support people while acident
<p>Vấn đề là khi con người chúng ta rơi vào trong nhũng tình huống nguy hiểm, khẩn cấp và không thể tìm được sự trợ giúp:</p>
<p>- Lạc vào rừng rậm, bị chảy máu tay, bị rắn cắn, dẫm phải kim tiêm, gặp người bị tai nạn...   </p>
<p>- Trong những tình huống như vậy đa số trong chúng ta rất bối rối và không biết cách làm tốt nhất trong tình huống này.</p>
<p>Ngoài ra chúng ta có thể ngoài vùng phủ sóng, ko có kết nối internet, ko tìm đc người giúp quanh mình hoặc gọi điện hay tìm sự hổ trợ sẻ khó khăn và chậm trể. </p>
<p>Vậy một ứng dụng có khả năng tư vấn giải quyết vấn đề lúc này là cực kỳ cần thiết. Nó đơn giản là nói với bạn nên làm gì tiếp theo, gợi ý một vài giải pháp , những lựa chọn mà bạn có thể có. Ví dụ: Khi một người lạc vào 1 khu rừng, đêm gần tối và thú giử vây quanh, SuBot gợi ý cho bạn tìm đá cuội và tạo lữa một cách chi tiết. hoặc nói với bạn nên tìm một tán lá cây để ngủ, hay gợi ý một vài thứ đồ ăn từ cây cối xung quanh..</p>
<p>Ví dụ khi bạn bị rắn cắn. bạn sẻ được hỏi rắn màu gì ở đâu và chỉ cách sơ cứu nhanh hơn gọi nhân viên y tế đến khẩn cấp. </p>
<p>Ví dụ khi bạn gặp người đuối nước. Bạn cần làm gì khi đưa được nạn nhân lên bờ, hô hấp nhân tạo ngay? nhịp điệu bao nhiêu? tư thế tay thế nào?. Thực ra ta cần xốc nạn nhân lên trước cho nước ra khỏi phổi rồi mới làm các biện pháp tiếp theo...</p>
<p>Bạn làm gì khi dẫm phải kim tiêm, bạn làm gì khi chung cư bị cháy? bạn làm gì khi xe máy đột nhiên chết máy? bạn làm gì để tố giác một hành vi xấu với cơ quan có thẩm quyền chính xác hơn là 113. </p>
<p>Sự gợi ý này dường như cực kỳ hữu ích trong vô vàn trường hợp. </p>
<p>Về mặt kỹ thuật ứng dụng sẻ được cài và dùng ở 2 chế độ online và offline.</p>
<p>Cần hiểu được con người nói gì. và intent chính xác được lưu ở local. </p>
________________
Các vấn đề về công nghệ được nhận diện: 
<p>0. Vào app khi cài đặt lần đầu thì chọn ngôn ngữ luôn. Từ sau đó mặc định ngôn ngữ đều xác định với độ ưu tiên là nó và trong trường hợp ngoại lệ sẻ là English. </p>
<p>1. Khi có internet, khi bật app -> bật micro -> record âm thanh -> gửi tới google cloud api speech to text theo ngôn ngữ lựa chọn. Nếu khi cài chọn tiếng anh hoặc 1/12 ngôn ngữ android hổ trợ thì ko cần gọi google api.</p>
<p>2. Khi ko có internet, khi bật app thông báo đang sử dụng chế độ offline và confirm chấp nhận sử dụng offline đối với các ngôn ngữ ngoài 12 nn được hệ điều hành hổ trợ, và hiện danh sách tình huống có textbox search tình huống. </p>
<p>3. Sau khi có được text từ system hoặc từ api. Nếu có mạng tiếp tục call tới Luis.ai/ builtin NLU library để nhận diện intent. Trong trường hợp vẫn ngắt kết nối hoặc ko biết intent hiện list tình huống đã lọc bởi thuật toán trong app và cần người dùng chọn 1 item.</p>
<p>4. Sau khi chọn 1 item theo các thủ công bởi selfclick hoặc tự động bởi NLU/api thì tiến hành xử lý thông tin đầu vào của tình huống .</p>
<p>Vd: Khi bị rắn cắn ta cần hỏi xem con rắn màu gì, cắn ở đâu, khoảng bao lâu rồi... bước này gọi là Q/A các input cần thiết cho quá trình tư vấn cách giải quyết vấn đề. </p>
<p>5. Sau quá trình Q/A về các yếu tố cần thiết, thì sẻ swich case và đưa ra giải pháp. Về cơ bản toàn bộ text/html của cách giải quyết luôn lưu ở local db. nhưng image/video animation sẻ được tải từ internet nếu có mạng. Trong trường hợp ko có mạng cần tạo ra phương pháp trực quan và sinh động bằng html css.</p>
